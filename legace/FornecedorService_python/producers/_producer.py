from io import BytesIO
from confluent_kafka import Producer, KafkaException
from dotenv import load_dotenv
from utils.avro_services import serialize_avro
from avro.io import BinaryEncoder, DatumWriter
from avro.schema import parse
import json


def load_schema(schema_path):
    with open(schema_path, 'r') as f:
        schema = json.load(f)
    return schema

def produce_message(topic, key, value, schema_path, bootstrap_servers):
    load_dotenv()
    if not validate_messgae_with_schema(value,schema_path):
        print("Mensagem fora do padrão do schema")
        print(f"Tópico: {topic}, schema_path: {schema_path}")
        print(json.dumps(value, indent=4))
        return

    schema = load_schema(schema_path)
    producer = Producer({"bootstrap.servers": bootstrap_servers})
    avro_data = serialize_avro(value, schema)

    try:
        producer.produce(topic, key=key, value=avro_data)
        producer.flush()
        print(f"\nMensagem produzida pelo tópico: {topic}\n")
        print(json.dumps(value, indent=4))
    except KafkaException as e:
        print(f"Erro ao produzir mensagem: {e}")


def validate_messgae_with_schema(message, schema_path):
    with open(schema_path, 'r') as f:
        schema = parse(json.dumps(json.load(f)))
    try:
        writer = DatumWriter(schema)
        bytes_writer = BytesIO()
        encoder = BinaryEncoder(bytes_writer)
        writer.write(message, encoder)
        return True
    except Exception as e:
        print(f"Erro de validação na mensagem: {e}")
        return False