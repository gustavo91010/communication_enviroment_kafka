from consumers.financial import start_financial_consumer
from producers.producer_financial import start_financial_producer
from producers.producer_order import start_order_producer
from consumers.order import start_order_consumer

if __name__ == "__main__":
    import threading
    
    consumers=[
        threading.Thread(target=start_financial_consumer),
        threading.Thread(target=start_order_consumer),
        # threading.Thread(target=start_order_producer)
    ]
    
    for consumer in consumers:
        consumer.start()

    for consumer in consumers:
        consumer.join()