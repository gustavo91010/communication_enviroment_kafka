from producers.logistics_order_preparation import start_logistic_producer
from consumers._consumer import consume_messages
from dotenv import load_dotenv
import json
import os

from services.producer_service import build_message_logistc_request


def load_schema(schema_path):
    with open(schema_path, 'r') as f:
        schema = json.load(f)
    return schema

def update_financial_consumer():
    load_dotenv()
    topic = os.getenv("TOPIC_FINANCIAL_UPDATE")
    schema = load_schema("utils/schemas/financial_order_update_schema.json")

    while True:
        financial_update = consume_messages(topic, schema)
        if financial_update:
            message_logistc_producer=  build_message_logistc_request(financial_update)
            yield start_logistic_producer(message_logistc_producer)
