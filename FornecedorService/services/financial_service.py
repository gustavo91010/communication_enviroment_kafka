import random
from producers.producer_financial import start_financial_producer


def process_financial_request(message):
    message["status"] = "processed"

    totalPrice = 0  

    for item in message["items"]:
        item["unitPrice"] = round(random.uniform(0.01, 120.00), 2)
        item["totalPrice"] = round(
            item["unitPrice"] * item["quantity"], 2
        )  # O ,2 arredonda o valor para 2 casas decimais
        totalPrice += item["totalPrice"]

    message["totalPrice"] = round(totalPrice, 2)

    start_financial_producer(message)
    # return message


# if __name__ == "__main__":
#     for order in start_order_consumer():
#         process_financial_request(order)
