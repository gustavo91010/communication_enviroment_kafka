import json
from services.financial_service import process_financial_update
from services.consumer import consume_messages

def load_schema(schema_path):
    with open(schema_path, 'r') as f:
        schema = json.load(f)
    return schema


def start_financial_consumer():
    schema = load_schema("utils/schemas/financial_request_schema.json")
    
    while True:
        message = consume_messages("TOPIC_FINANCIAL_REQUEST", schema)
        if message:
            yield process_financial_update(message)
