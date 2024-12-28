from utils.consumer_service import consume_messages

def start_financial_consumer():
    topic = "teste-api_003"
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
    
    consume_messages(topic, FINANCEIAL_SCHEMA, KAFKA_CONFIG)