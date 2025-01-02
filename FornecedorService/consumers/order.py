from dotenv import load_dotenv
import os

from utils.consumer_service import consume_messages

load_dotenv()


def start_order_consumer():
    topic = os.getenv("TOPIC_ORDER")
    schema = {
        "type": "record",
        "name": "Order",
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
    config = {
        "bootstrap.servers": "localhost:29092",
        "group.id": "fornecedor-service-consumer",
        "auto.offset.reset": "earliest",
    }

    consume_messages(topic, schema, config)
