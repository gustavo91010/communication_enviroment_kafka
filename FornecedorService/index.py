import threading
from consumers.client_order_request import start_order_consumer
from consumers.financial_order_update import update_financial_consumer
from consumers.logistics_delivery_close import close_logistic_consumer
from consumers.logistics_order_update import update_logistic_consumer


def _start_order_consumer():
    for order in start_order_consumer():
        print("\nMensagem do Fornecedor:\n", order)


def _update_financial_consumer():
    for financial_update in update_financial_consumer():
        print("\nMensagem de Logística:\n", financial_update)


def _update_logistic_consumer():
    for logistic_update in update_logistic_consumer():
        print("\nMensagem de Orçamento:\n", logistic_update)


def _close_logistic_consumer():
    for close_logistic in close_logistic_consumer():
        print("\nMensagem de Orçamento:\n", close_logistic)


if __name__ == "__main__":
    
    order_thread = threading.Thread(target=_start_order_consumer)
    logistic_thread = threading.Thread(target=_update_financial_consumer)
    budget_thread = threading.Thread(target=_update_logistic_consumer)
    close_thread = threading.Thread(target=_close_logistic_consumer)

    # iniciando as threads
    order_thread.start()
    logistic_thread.start()
    budget_thread.start()
    close_thread.start()

    # encerrando as threads
    order_thread.join()
    logistic_thread.join()
    budget_thread.join()
    close_thread.join()
