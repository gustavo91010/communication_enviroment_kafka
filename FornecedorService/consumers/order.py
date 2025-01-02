
from utils.consumer_service import consume_messages



def start_order_consumer():
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
 

    consume_messages("TOPIC_ORDER", schema)
