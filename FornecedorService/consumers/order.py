import json
from services.financial_service import process_financial_request
from utils.consumer_service import consume_messages

def load_schema(schema_path):
    with open(schema_path, 'r') as f:
        schema = json.load(f)
    return schema


def start_order_consumer():
    schema = load_schema("utils/schemas/order_schema.json")
    
    while True:
        message = consume_messages("TOPIC_ORDER", schema)
        if message:
            yield process_financial_request(message)
