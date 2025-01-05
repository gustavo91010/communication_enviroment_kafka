import json
import os
from dotenv import load_dotenv
from consumers._consumer import consume_messages
from producers.producer_financial import start_financial_producer
from services.producer_service import build_message_financial_request

def load_schema(schema_path):
    with open(schema_path, 'r') as f:
        schema = json.load(f)
    return schema


def start_order_consumer():
    load_dotenv()
    schema = load_schema("utils/schemas/order_schema.json")
    topic = os.getenv("TOPIC_FINANCIAL_UPDATE")

    while True:
        client_order = consume_messages(topic, schema)
        if client_order:
            request_financial =build_message_financial_request(client_order)
            yield start_financial_producer(request_financial)