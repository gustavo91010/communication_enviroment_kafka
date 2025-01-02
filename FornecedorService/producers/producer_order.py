from dotenv import load_dotenv
import os
from utils.producer_service import produce_message


FINANCEIAL_SCHEMA = {
    "type": "record",
    "name": "Order",
    "namespace": "com.ajudaqui.gestor360_api.kafka.entity",
    "fields": [
        {"name": "orderId", "type": "string"},
        {
            "name": "items",
            "type": {
                "type": "array",
                "items": {
                    "type": "record",
                    "name": "OrderItem",
                    "fields": [
                        {"name": "name", "type": "string"},
                        {"name": "brand", "type": "string"},
                        {"name": "quantity", "type": "double"},
                    ],
                },
            },
        },
        {"name": "timestamp", "type": "string"},
    ],
}
KAFKA_CONFIG = {
    "bootstrap.servers": "localhost:29092",
    "group.id": "financial-service-consumer",
    "auto.offset.reset": "earliest",
}


def start_order_producer():
    load_dotenv()
    topic = os.getenv("TOPIC_ORDER")

    message = {
        "orderId": "12345",
        "items": [
            {"name": "Macarrão", "brand": "Marca X", "quantity": 5.5},
            {"name": "Arroz", "brand": "Marca Y", "quantity": 10.0},
        ],
        "timestamp": "2025-01-01T12:00:00",
    }

    # def produce_to_financial(topic, message):
    bootstrap_servers = "localhost:29092"

    produce_message(topic, None, message, FINANCEIAL_SCHEMA, bootstrap_servers)
