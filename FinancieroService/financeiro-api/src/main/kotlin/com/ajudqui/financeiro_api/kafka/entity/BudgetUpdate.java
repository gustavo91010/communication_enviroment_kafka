/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package kafka.entity;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class BudgetUpdate extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -803818231936078793L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"BudgetUpdate\",\"namespace\":\"kafka.entity\",\"fields\":[{\"name\":\"code\",\"type\":\"string\"},{\"name\":\"status\",\"type\":\"string\"},{\"name\":\"timestamp\",\"type\":\"string\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<BudgetUpdate> ENCODER =
      new BinaryMessageEncoder<BudgetUpdate>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<BudgetUpdate> DECODER =
      new BinaryMessageDecoder<BudgetUpdate>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<BudgetUpdate> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<BudgetUpdate> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<BudgetUpdate> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<BudgetUpdate>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this BudgetUpdate to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a BudgetUpdate from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a BudgetUpdate instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static BudgetUpdate fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

   private java.lang.CharSequence code;
   private java.lang.CharSequence status;
   private java.lang.CharSequence timestamp;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public BudgetUpdate() {}

  /**
   * All-args constructor.
   * @param code The new value for code
   * @param status The new value for status
   * @param timestamp The new value for timestamp
   */
  public BudgetUpdate(java.lang.CharSequence code, java.lang.CharSequence status, java.lang.CharSequence timestamp) {
    this.code = code;
    this.status = status;
    this.timestamp = timestamp;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return code;
    case 1: return status;
    case 2: return timestamp;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: code = (java.lang.CharSequence)value$; break;
    case 1: status = (java.lang.CharSequence)value$; break;
    case 2: timestamp = (java.lang.CharSequence)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'code' field.
   * @return The value of the 'code' field.
   */
  public java.lang.CharSequence getCode() {
    return code;
  }


  /**
   * Sets the value of the 'code' field.
   * @param value the value to set.
   */
  public void setCode(java.lang.CharSequence value) {
    this.code = value;
  }

  /**
   * Gets the value of the 'status' field.
   * @return The value of the 'status' field.
   */
  public java.lang.CharSequence getStatus() {
    return status;
  }


  /**
   * Sets the value of the 'status' field.
   * @param value the value to set.
   */
  public void setStatus(java.lang.CharSequence value) {
    this.status = value;
  }

  /**
   * Gets the value of the 'timestamp' field.
   * @return The value of the 'timestamp' field.
   */
  public java.lang.CharSequence getTimestamp() {
    return timestamp;
  }


  /**
   * Sets the value of the 'timestamp' field.
   * @param value the value to set.
   */
  public void setTimestamp(java.lang.CharSequence value) {
    this.timestamp = value;
  }

  /**
   * Creates a new BudgetUpdate RecordBuilder.
   * @return A new BudgetUpdate RecordBuilder
   */
  public static kafka.entity.BudgetUpdate.Builder newBuilder() {
    return new kafka.entity.BudgetUpdate.Builder();
  }

  /**
   * Creates a new BudgetUpdate RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new BudgetUpdate RecordBuilder
   */
  public static kafka.entity.BudgetUpdate.Builder newBuilder(kafka.entity.BudgetUpdate.Builder other) {
    if (other == null) {
      return new kafka.entity.BudgetUpdate.Builder();
    } else {
      return new kafka.entity.BudgetUpdate.Builder(other);
    }
  }

  /**
   * Creates a new BudgetUpdate RecordBuilder by copying an existing BudgetUpdate instance.
   * @param other The existing instance to copy.
   * @return A new BudgetUpdate RecordBuilder
   */
  public static kafka.entity.BudgetUpdate.Builder newBuilder(kafka.entity.BudgetUpdate other) {
    if (other == null) {
      return new kafka.entity.BudgetUpdate.Builder();
    } else {
      return new kafka.entity.BudgetUpdate.Builder(other);
    }
  }

  /**
   * RecordBuilder for BudgetUpdate instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<BudgetUpdate>
    implements org.apache.avro.data.RecordBuilder<BudgetUpdate> {

    private java.lang.CharSequence code;
    private java.lang.CharSequence status;
    private java.lang.CharSequence timestamp;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(kafka.entity.BudgetUpdate.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.code)) {
        this.code = data().deepCopy(fields()[0].schema(), other.code);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.status)) {
        this.status = data().deepCopy(fields()[1].schema(), other.status);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.timestamp)) {
        this.timestamp = data().deepCopy(fields()[2].schema(), other.timestamp);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
    }

    /**
     * Creates a Builder by copying an existing BudgetUpdate instance
     * @param other The existing instance to copy.
     */
    private Builder(kafka.entity.BudgetUpdate other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.code)) {
        this.code = data().deepCopy(fields()[0].schema(), other.code);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.status)) {
        this.status = data().deepCopy(fields()[1].schema(), other.status);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.timestamp)) {
        this.timestamp = data().deepCopy(fields()[2].schema(), other.timestamp);
        fieldSetFlags()[2] = true;
      }
    }

    /**
      * Gets the value of the 'code' field.
      * @return The value.
      */
    public java.lang.CharSequence getCode() {
      return code;
    }


    /**
      * Sets the value of the 'code' field.
      * @param value The value of 'code'.
      * @return This builder.
      */
    public kafka.entity.BudgetUpdate.Builder setCode(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.code = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'code' field has been set.
      * @return True if the 'code' field has been set, false otherwise.
      */
    public boolean hasCode() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'code' field.
      * @return This builder.
      */
    public kafka.entity.BudgetUpdate.Builder clearCode() {
      code = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'status' field.
      * @return The value.
      */
    public java.lang.CharSequence getStatus() {
      return status;
    }


    /**
      * Sets the value of the 'status' field.
      * @param value The value of 'status'.
      * @return This builder.
      */
    public kafka.entity.BudgetUpdate.Builder setStatus(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.status = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'status' field has been set.
      * @return True if the 'status' field has been set, false otherwise.
      */
    public boolean hasStatus() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'status' field.
      * @return This builder.
      */
    public kafka.entity.BudgetUpdate.Builder clearStatus() {
      status = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'timestamp' field.
      * @return The value.
      */
    public java.lang.CharSequence getTimestamp() {
      return timestamp;
    }


    /**
      * Sets the value of the 'timestamp' field.
      * @param value The value of 'timestamp'.
      * @return This builder.
      */
    public kafka.entity.BudgetUpdate.Builder setTimestamp(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.timestamp = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'timestamp' field has been set.
      * @return True if the 'timestamp' field has been set, false otherwise.
      */
    public boolean hasTimestamp() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'timestamp' field.
      * @return This builder.
      */
    public kafka.entity.BudgetUpdate.Builder clearTimestamp() {
      timestamp = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public BudgetUpdate build() {
      try {
        BudgetUpdate record = new BudgetUpdate();
        record.code = fieldSetFlags()[0] ? this.code : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.status = fieldSetFlags()[1] ? this.status : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.timestamp = fieldSetFlags()[2] ? this.timestamp : (java.lang.CharSequence) defaultValue(fields()[2]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<BudgetUpdate>
    WRITER$ = (org.apache.avro.io.DatumWriter<BudgetUpdate>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<BudgetUpdate>
    READER$ = (org.apache.avro.io.DatumReader<BudgetUpdate>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeString(this.code);

    out.writeString(this.status);

    out.writeString(this.timestamp);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.code = in.readString(this.code instanceof Utf8 ? (Utf8)this.code : null);

      this.status = in.readString(this.status instanceof Utf8 ? (Utf8)this.status : null);

      this.timestamp = in.readString(this.timestamp instanceof Utf8 ? (Utf8)this.timestamp : null);

    } else {
      for (int i = 0; i < 3; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.code = in.readString(this.code instanceof Utf8 ? (Utf8)this.code : null);
          break;

        case 1:
          this.status = in.readString(this.status instanceof Utf8 ? (Utf8)this.status : null);
          break;

        case 2:
          this.timestamp = in.readString(this.timestamp instanceof Utf8 ? (Utf8)this.timestamp : null);
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










