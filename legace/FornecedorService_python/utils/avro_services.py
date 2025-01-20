import io
import json
from avro.schema import parse
from avro.io import DatumWriter, DatumReader, BinaryEncoder, BinaryDecoder


def serialize_avro(data, json_schema):
    schema_json = json.dumps(json_schema)
    schema = parse(schema_json)
    writer = DatumWriter(schema)
    bytes_io = io.BytesIO()
    encoder = BinaryEncoder(bytes_io)
    writer.write(data, encoder)
    return bytes_io.getvalue()



def deserialize_avro(avro_data, schema):
    try:
        bytes_io = io.BytesIO(avro_data)
        bytes_io.seek(5) 
        decoder = BinaryDecoder(bytes_io)
        reader = DatumReader(schema)
        return reader.read(decoder)
    except Exception as e:
        print(f"Erro ao processar Avro: {e}")
        raise
#