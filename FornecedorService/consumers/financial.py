from services.consumer import consume_messages


def start_financial_consumer():
 
    schema = {
        "type": "record",
        "name": "financial",
        "fields": [
            {"name": "code", "type": "string"},
            {"name": "status", "type": "string"},
            {"name": "totalPrice", "type": "string"},
        ],
    }

    consume_messages("TOPIC_ORDER", schema)
