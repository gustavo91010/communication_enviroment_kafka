from confluent_kafka import Producer, KafkaException
from utils.avro_services import serialize_avro


def produce_message(topic, key, value, schema, bootstrap_servers):
    producer = Producer({"bootstrap.servers": bootstrap_servers})
    avro_data = serialize_avro(value, schema)

    try:
        producer.produce(topic, key=key, value=avro_data)
        producer.flush()
        print(f"para o tópico {topic}")
        print(f"Mensagem produzida e enviada: {value}")
    except KafkaException as e:
        print(f"Erro ao produzir mensagem: {e}")
