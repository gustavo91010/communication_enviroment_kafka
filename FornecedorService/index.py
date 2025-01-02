import json
from consumers.order import start_order_consumer

if __name__ == "__main__":
    for order in start_order_consumer():
        print("\nmensagem do fornecedor\n")
