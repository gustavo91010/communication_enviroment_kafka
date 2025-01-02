import json
import os
from dotenv import load_dotenv
from utils.producer_service import produce_message

def load_schema(schema_path):
    with open(schema_path, 'r') as f:
        schema = json.load(f)
    return schema


def start_financial_producer(message_financial):
    load_dotenv()
    topic = os.getenv("TOPIC_FINANCIAL_REQUEST")
    schema = load_schema("utils/schemas/financial_request_schema.json")
    bootstrap_servers = os.getenv("BOOTSTRAP_SERVERS")
    
    produce_message(topic, None, message_financial, schema, bootstrap_servers)
