from producers.logistics_order_preparation import start_logistic_producer
from consumers._consumer import consume_messages
from dotenv import load_dotenv
import json

from jsonschema import validate, ValidationError

from services.producer_service import build_message_logistc_request

def load_schema(schema_path):
    with open(schema_path, 'r') as f:
        schema = json.load(f)
    return schema

def update_financial_consumer():
    load_dotenv()
    schema = load_schema("utils/schemas/financial_order_update_schema.json")

    while True:
        financial_update = consume_messages("TOPIC_FINANCIAL_UPDAT", schema)
        if financial_update:
            message_logistc_producer=  build_message_logistc_request(financial_update)
            yield start_logistic_producer(message_logistc_producer)
            
            
# Função para validar a mensagem com o schema
def validate_message_with_schema(message, schema):
    try:
        validate(instance=message, schema=schema)
        return True  
    except ValidationError as e:
        print(f"Erro de validação na mensagem: {e.message}")
        print("message com modelo recuasdo:")
        print(message)
        return False  
