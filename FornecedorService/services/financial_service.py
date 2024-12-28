from producers.producer_financial import produce_to_financial

def process_financial_message(message):
    print(f"Mensagem recebida no tópico 'financial': {message}")
    # Manipula a mensagem e produz para outro tópico, se necessário
    processed_message = {
        "status": "processed",
        "original": message,
    }
    produce_to_financial("processed_financial", processed_message)
