import json
import os
from dotenv import load_dotenv
from services.producer import produce_message

def load_schema(schema_path):
    with open(schema_path, 'r') as f:
        schema = json.load(f)
    return schema


def start_financial_producer(message_financial):
    load_dotenv()
    topic = os.getenv("TOPIC_FINANCIAL_UPDATE")
    schema = load_schema("utils/schemas/financial_update_schema.json")
    bootstrap_servers = os.getenv("BOOTSTRAP_SERVERS")
    
    produce_message(topic, None, message_financial, schema, bootstrap_servers)
