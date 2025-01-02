import json
from consumers.financial_request import start_financial_consumer

if __name__ == "__main__":
    for financial in start_financial_consumer():
        print("\nMensagem prudzida pelo Financeiro.\n")
