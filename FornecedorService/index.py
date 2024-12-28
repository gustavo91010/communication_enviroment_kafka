import io
import json
import random
import uuid
from avro.schema import Parse
from avro.io import DatumWriter,DatumReader, BinaryEncoder,BinaryDecoder
from confluent_kafka import Producer, Consumer, KafkaException, KafkaError

# Definindo o esquema Avro
AVRO_SCHEMA = {
    "type": "record",
    "name": "Pedido",
    "fields": [
        {"name": "id", "type": "int"},
        {"name": "usersId", "type": "int"},
        {"name": "name", "type": "string"},
        {"name": "brand", "type": "string"},
        {"name": "quantity", "type": "double"},
    ],
}

KAFKA_CONFIG = {
    "bootstrap.servers": "localhost:29092",
    "group.id": "fornecedor-service-consumer",
    "auto.offset.reset": "earliest",
}

# Convertendo o esquema para JSON
schema_json = json.dumps(AVRO_SCHEMA)
schema = Parse(schema_json)

def serialize_avro(data, schema):
    writer = DatumWriter(schema)
    bytes_io = io.BytesIO() 
    encoder = BinaryEncoder(bytes_io)
    writer.write(data, encoder)
    return bytes_io.getvalue()  


def produce_message(topic, key, value):
    producer = Producer({"bootstrap.servers": KAFKA_CONFIG["bootstrap.servers"]})
    avro_data = serialize_avro(value, schema)
    
    try:
        producer.produce(topic, key=key, value=avro_data)
        producer.flush()
        print(f"Mensagem enviada: {value} para o tópico {topic}")
    except KafkaException as e:
        print(f"Erro ao produzir mensagem: {e}")

def deserialize_avro(avro_data, schema):
    bytes_io = io.BytesIO(avro_data)
    decoder = BinaryDecoder(bytes_io)
    reader = DatumReader(schema)
    return reader.read(decoder)

def consume_messages(topic):
    consumer = Consumer(KAFKA_CONFIG)
    consumer.subscribe([topic])
    try:
        while True:
            msg = consumer.poll(1.0)  # Intervalo entre verificação de mensagens
            if msg is None:
                continue
            if msg.error():
                if msg.error().code() != KafkaError._PARTITION_EOF:
                    print(f"Erro no kafka: {msg.error()}")
                continue
            
            avro_data = deserialize_avro(msg.value(), schema)
            print(f"Mensagem recebida (Avro): {avro_data}")
            # consumer.commit(msg)  # Confirma o offset após a leitura
    except KeyboardInterrupt:
        print("Encerrando consumidor")
    finally:
        consumer.close()

if __name__ == "__main__":
    topic = "teste-api_002"
    key = "pedido_001"
    
    value = {
        'id': random.randint(1,100),
        'usersId': random.randint(1,100),
        'name': 'Pedido XYZ',
        'brand': 'Marca ABC',
        'quantity': round(random.uniform(0.01, 100.00),2)
    }
    
    produce_message(topic, key, value)

    # Consumir mensagens
    consume_messages(topic)

print("foi... ve se foi mesmo...")
