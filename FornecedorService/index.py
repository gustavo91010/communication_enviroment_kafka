from consumers.consumer_financial import start_financial_consumer
from producers.producer_financial import start_financial_producer

if __name__ == "__main__":
    import threading
    
    consumers=[
        threading.Thread(target=start_financial_consumer),
        threading.Thread(target=start_financial_producer)
    ]
    
    for consumer in consumers:
        consumer.start()

    # for consumer in consumers:
    #     consumer.join()