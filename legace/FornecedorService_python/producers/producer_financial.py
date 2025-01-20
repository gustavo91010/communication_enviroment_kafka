import os
from producers._producer import produce_message

def start_financial_producer(message_financial):
    topic = os.getenv("TOPIC_FINANCIAL_REQUEST")
    schema = "utils/schemas/financial_request_schema.json"
    bootstrap_servers = os.getenv("BOOTSTRAP_SERVERS")
    
    produce_message(topic, None, message_financial, schema, bootstrap_servers)
