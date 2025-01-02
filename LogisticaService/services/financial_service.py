import random
from datetime import datetime
from producers.financial_update import start_financial_producer


def process_financial_update(message):

    status= "reproved" if random.random() <= 0.95 else "aproved"

    message = {
        "code": message["code"],
        "status": status,
        "timestamp": datetime.now().isoformat()
    }

    start_financial_producer(message)
