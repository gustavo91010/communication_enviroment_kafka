from consumers._consumer import consume_messages
from dotenv import load_dotenv
import json
import os



def load_schema(schema_path):
    with open(schema_path, 'r') as f:
        schema = json.load(f)
    return schema

def close_logistic_consumer():
    load_dotenv()
    topic = os.getenv("TOPIC_LOGISTIC_CLOSE")
    schema = load_schema("utils/schemas/logistic_close_schema.json")
    consume_messages(topic, schema)
   