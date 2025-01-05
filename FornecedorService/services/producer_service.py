import random
from producers.producer_financial import start_financial_producer
from datetime import datetime



def build_message_financial_request(message):
    message["status"] = "processed"

    totalPrice = 0  

    for item in message["items"]:
        item["unitPrice"] = round(random.uniform(0.01, 120.00), 2)
        item["totalPrice"] = round(
            item["unitPrice"] * item["quantity"], 2
        )  # O ,2 arredonda o valor para 2 casas decimais
        totalPrice += item["totalPrice"]

    message["totalPrice"] = round(totalPrice, 2)
    return message


def build_message_logistc_request(message):
    message["timestamp"]= datetime.now().isoformat()
    message["volume"]=random.randint(50, 100)
    message["weight"]=round(random.uniform(10.01, 20.00), 2)
    message["destination"]="rua lalala dos santos, UF: BO"
    
    message.pop("status", None)
    
    return message
 