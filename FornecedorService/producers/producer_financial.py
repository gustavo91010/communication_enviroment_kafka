import random
import uuid
from dotenv import load_dotenv
import os
from utils.producer_service import produce_message


FINANCEIAL_SCHEMA = {
    "type": "record",
    "name": "financial",
    "fields": [
        {"name": "code", "type": "string"},
        {"name": "status", "type": "string"},
        {"name": "totalPrice", "type": "string"},
    ],
}
KAFKA_CONFIG = {
    "bootstrap.servers": "localhost:29092",
    "group.id": "financial-service-consumer",
    "auto.offset.reset": "earliest",
}


def start_financial_producer():
    load_dotenv()
    topic = os.getenv("TOPIC_FINANCIAL_REQUEST")
    
    message = {
        "code": str(uuid.uuid4()), 
        "status": "SOLICITACAO",
        "totalPrice": str(
            round(random.uniform(0.01, 100.00), 2)
        ),  
    }
    # def produce_to_financial(topic, message):
    bootstrap_servers = "localhost:29092"

    produce_message(topic, None, message, FINANCEIAL_SCHEMA, bootstrap_servers)
