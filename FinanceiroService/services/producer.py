import json
from confluent_kafka import Producer, KafkaException
from utils.avro_services import serialize_avro


def produce_message(topic, key, value, schema, bootstrap_servers):
    producer = Producer({"bootstrap.servers": bootstrap_servers})
    avro_data = serialize_avro(value, schema)

    try:
        producer.produce(topic, key=key, value=avro_data)
        producer.flush()
        print(f"\nAtualização do financeiro para a solicitação code: {value['code']}\n")
        print(json.dumps(value, indent=4))
    except KafkaException as e:
        print(f"Erro ao produzir mensagem: {e}")
