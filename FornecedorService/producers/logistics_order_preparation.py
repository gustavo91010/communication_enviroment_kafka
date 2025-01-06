import os
from producers._producer import produce_message

def start_logistic_producer(message_financial):
    topic = os.getenv("TOPIC_LOGISTC_PRODUCER")
    schema = "utils/schemas/topic_logistic_producer_schema.json"

    bootstrap_servers = os.getenv("BOOTSTRAP_SERVERS")
    
    produce_message(topic, None, message_financial, schema, bootstrap_servers)
