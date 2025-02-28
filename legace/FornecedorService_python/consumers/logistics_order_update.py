from consumers._consumer import consume_messages
from dotenv import load_dotenv
import json



def load_schema(schema_path):
    with open(schema_path, 'r') as f:
        schema = json.load(f)
    return schema

def update_logistic_consumer():
    load_dotenv()
    schema = load_schema("utils/schemas/logistic_status_update_schema.json")
    consume_messages("TOPIC_LOGISTIC_CONSUMER", schema)
   