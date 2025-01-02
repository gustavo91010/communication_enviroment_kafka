import json
import os
from dotenv import load_dotenv
from avro.schema import Parse
from confluent_kafka import Consumer, KafkaError
from utils.avro_services import deserialize_avro


load_dotenv()
kafka_config = {
    "bootstrap.servers": os.getenv("BOOTSTRAP_SERVERS"),
    "group.id": os.getenv("GROUP_ID"),
    "auto.offset.reset": os.getenv("AUTO_OFFSET_RESET"),
}


def consume_messages(topic, schema_json):
    schema = Parse(json.dumps(schema_json))  # Convertendo o JSON em Avro

    consumer = Consumer(kafka_config)
    consumer.subscribe([os.getenv(topic)])
    try:
        while True:
            msg = consumer.poll(1.0)
            if msg is None:
                continue
            if msg.error():
                if msg.error().code() != KafkaError._PARTITION_EOF:
                    print(f"Erro no kafka: {msg.error()}")
                continue

            try:
                avro_data = deserialize_avro(msg.value(), schema)
                print(f"Mensagem recebida (Avro): {avro_data}")
            except Exception as e:
                print(f"Erro ao desserializar a mensagem: {e}")
    except KeyboardInterrupt:
        print("Encerrando consumidor")
    finally:
        consumer.close()
