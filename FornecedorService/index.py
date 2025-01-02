
from consumers.order import start_order_consumer

if __name__ == "__main__":
    for order in start_order_consumer():
        print(order)




# if __name__ == "__main__":
#     import threading
    
#     consumers=[
#         # threading.Thread(target=start_financial_consumer),
#         threading.Thread(target=start_order_consumer),
#         # threading.Thread(target=start_order_producer)
#     ]
    
#     for consumer in consumers:
#         consumer.start()

#     for consumer in consumers:
#         consumer.join()
    
#     # for processed_order in start_order_consumer():
#     #   print(processed_order)