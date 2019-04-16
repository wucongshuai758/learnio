// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: SubscribeResp.proto

package com.wcs.learn.netty.protobuf;

public final class SubscribeRespProto {
  private SubscribeRespProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface SubscribeRespOrBuilder extends
      // @@protoc_insertion_point(interface_extends:SubscribeResp)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>required int32 subReqId = 1;</code>
     */
    boolean hasSubReqId();
    /**
     * <code>required int32 subReqId = 1;</code>
     */
    int getSubReqId();

    /**
     * <code>required string respCode = 2;</code>
     */
    boolean hasRespCode();
    /**
     * <code>required string respCode = 2;</code>
     */
    String getRespCode();
    /**
     * <code>required string respCode = 2;</code>
     */
    com.google.protobuf.ByteString
        getRespCodeBytes();

    /**
     * <code>repeated string desc = 3;</code>
     */
    java.util.List<String>
        getDescList();
    /**
     * <code>repeated string desc = 3;</code>
     */
    int getDescCount();
    /**
     * <code>repeated string desc = 3;</code>
     */
    String getDesc(int index);
    /**
     * <code>repeated string desc = 3;</code>
     */
    com.google.protobuf.ByteString
        getDescBytes(int index);
  }
  /**
   * Protobuf type {@code SubscribeResp}
   */
  public  static final class SubscribeResp extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:SubscribeResp)
      SubscribeRespOrBuilder {
    // Use SubscribeResp.newBuilder() to construct.
    private SubscribeResp(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private SubscribeResp() {
      subReqId_ = 0;
      respCode_ = "";
      desc_ = com.google.protobuf.LazyStringArrayList.EMPTY;
    }

    @Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private SubscribeResp(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 8: {
              bitField0_ |= 0x00000001;
              subReqId_ = input.readInt32();
              break;
            }
            case 18: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000002;
              respCode_ = bs;
              break;
            }
            case 26: {
              com.google.protobuf.ByteString bs = input.readBytes();
              if (!((mutable_bitField0_ & 0x00000004) == 0x00000004)) {
                desc_ = new com.google.protobuf.LazyStringArrayList();
                mutable_bitField0_ |= 0x00000004;
              }
              desc_.add(bs);
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        if (((mutable_bitField0_ & 0x00000004) == 0x00000004)) {
          desc_ = desc_.getUnmodifiableView();
        }
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return SubscribeRespProto.internal_static_SubscribeResp_descriptor;
    }

    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return SubscribeRespProto.internal_static_SubscribeResp_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              SubscribeResp.class, Builder.class);
    }

    private int bitField0_;
    public static final int SUBREQID_FIELD_NUMBER = 1;
    private int subReqId_;
    /**
     * <code>required int32 subReqId = 1;</code>
     */
    public boolean hasSubReqId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int32 subReqId = 1;</code>
     */
    public int getSubReqId() {
      return subReqId_;
    }

    public static final int RESPCODE_FIELD_NUMBER = 2;
    private volatile Object respCode_;
    /**
     * <code>required string respCode = 2;</code>
     */
    public boolean hasRespCode() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required string respCode = 2;</code>
     */
    public String getRespCode() {
      Object ref = respCode_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          respCode_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string respCode = 2;</code>
     */
    public com.google.protobuf.ByteString
        getRespCodeBytes() {
      Object ref = respCode_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        respCode_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int DESC_FIELD_NUMBER = 3;
    private com.google.protobuf.LazyStringList desc_;
    /**
     * <code>repeated string desc = 3;</code>
     */
    public com.google.protobuf.ProtocolStringList
        getDescList() {
      return desc_;
    }
    /**
     * <code>repeated string desc = 3;</code>
     */
    public int getDescCount() {
      return desc_.size();
    }
    /**
     * <code>repeated string desc = 3;</code>
     */
    public String getDesc(int index) {
      return desc_.get(index);
    }
    /**
     * <code>repeated string desc = 3;</code>
     */
    public com.google.protobuf.ByteString
        getDescBytes(int index) {
      return desc_.getByteString(index);
    }

    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      if (!hasSubReqId()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasRespCode()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeInt32(1, subReqId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 2, respCode_);
      }
      for (int i = 0; i < desc_.size(); i++) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 3, desc_.getRaw(i));
      }
      unknownFields.writeTo(output);
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, subReqId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, respCode_);
      }
      {
        int dataSize = 0;
        for (int i = 0; i < desc_.size(); i++) {
          dataSize += computeStringSizeNoTag(desc_.getRaw(i));
        }
        size += dataSize;
        size += 1 * getDescList().size();
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @Override
    public boolean equals(final Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof SubscribeResp)) {
        return super.equals(obj);
      }
      SubscribeResp other = (SubscribeResp) obj;

      boolean result = true;
      result = result && (hasSubReqId() == other.hasSubReqId());
      if (hasSubReqId()) {
        result = result && (getSubReqId()
            == other.getSubReqId());
      }
      result = result && (hasRespCode() == other.hasRespCode());
      if (hasRespCode()) {
        result = result && getRespCode()
            .equals(other.getRespCode());
      }
      result = result && getDescList()
          .equals(other.getDescList());
      result = result && unknownFields.equals(other.unknownFields);
      return result;
    }

    @Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptorForType().hashCode();
      if (hasSubReqId()) {
        hash = (37 * hash) + SUBREQID_FIELD_NUMBER;
        hash = (53 * hash) + getSubReqId();
      }
      if (hasRespCode()) {
        hash = (37 * hash) + RESPCODE_FIELD_NUMBER;
        hash = (53 * hash) + getRespCode().hashCode();
      }
      if (getDescCount() > 0) {
        hash = (37 * hash) + DESC_FIELD_NUMBER;
        hash = (53 * hash) + getDescList().hashCode();
      }
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static SubscribeResp parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static SubscribeResp parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static SubscribeResp parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static SubscribeResp parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static SubscribeResp parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static SubscribeResp parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static SubscribeResp parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static SubscribeResp parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static SubscribeResp parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static SubscribeResp parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(SubscribeResp prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @Override
    protected Builder newBuilderForType(
        BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code SubscribeResp}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:SubscribeResp)
        SubscribeRespOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return SubscribeRespProto.internal_static_SubscribeResp_descriptor;
      }

      protected FieldAccessorTable
          internalGetFieldAccessorTable() {
        return SubscribeRespProto.internal_static_SubscribeResp_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                SubscribeResp.class, Builder.class);
      }

      // Construct using com.wcs.learn.netty.protobuf.SubscribeRespProto.SubscribeResp.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      public Builder clear() {
        super.clear();
        subReqId_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        respCode_ = "";
        bitField0_ = (bitField0_ & ~0x00000002);
        desc_ = com.google.protobuf.LazyStringArrayList.EMPTY;
        bitField0_ = (bitField0_ & ~0x00000004);
        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return SubscribeRespProto.internal_static_SubscribeResp_descriptor;
      }

      public SubscribeResp getDefaultInstanceForType() {
        return SubscribeResp.getDefaultInstance();
      }

      public SubscribeResp build() {
        SubscribeResp result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public SubscribeResp buildPartial() {
        SubscribeResp result = new SubscribeResp(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.subReqId_ = subReqId_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.respCode_ = respCode_;
        if (((bitField0_ & 0x00000004) == 0x00000004)) {
          desc_ = desc_.getUnmodifiableView();
          bitField0_ = (bitField0_ & ~0x00000004);
        }
        result.desc_ = desc_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder clone() {
        return (Builder) super.clone();
      }
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.setField(field, value);
      }
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof SubscribeResp) {
          return mergeFrom((SubscribeResp)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(SubscribeResp other) {
        if (other == SubscribeResp.getDefaultInstance()) return this;
        if (other.hasSubReqId()) {
          setSubReqId(other.getSubReqId());
        }
        if (other.hasRespCode()) {
          bitField0_ |= 0x00000002;
          respCode_ = other.respCode_;
          onChanged();
        }
        if (!other.desc_.isEmpty()) {
          if (desc_.isEmpty()) {
            desc_ = other.desc_;
            bitField0_ = (bitField0_ & ~0x00000004);
          } else {
            ensureDescIsMutable();
            desc_.addAll(other.desc_);
          }
          onChanged();
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      public final boolean isInitialized() {
        if (!hasSubReqId()) {
          return false;
        }
        if (!hasRespCode()) {
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        SubscribeResp parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (SubscribeResp) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private int subReqId_ ;
      /**
       * <code>required int32 subReqId = 1;</code>
       */
      public boolean hasSubReqId() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required int32 subReqId = 1;</code>
       */
      public int getSubReqId() {
        return subReqId_;
      }
      /**
       * <code>required int32 subReqId = 1;</code>
       */
      public Builder setSubReqId(int value) {
        bitField0_ |= 0x00000001;
        subReqId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 subReqId = 1;</code>
       */
      public Builder clearSubReqId() {
        bitField0_ = (bitField0_ & ~0x00000001);
        subReqId_ = 0;
        onChanged();
        return this;
      }

      private Object respCode_ = "";
      /**
       * <code>required string respCode = 2;</code>
       */
      public boolean hasRespCode() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required string respCode = 2;</code>
       */
      public String getRespCode() {
        Object ref = respCode_;
        if (!(ref instanceof String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            respCode_ = s;
          }
          return s;
        } else {
          return (String) ref;
        }
      }
      /**
       * <code>required string respCode = 2;</code>
       */
      public com.google.protobuf.ByteString
          getRespCodeBytes() {
        Object ref = respCode_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (String) ref);
          respCode_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string respCode = 2;</code>
       */
      public Builder setRespCode(
          String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        respCode_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string respCode = 2;</code>
       */
      public Builder clearRespCode() {
        bitField0_ = (bitField0_ & ~0x00000002);
        respCode_ = getDefaultInstance().getRespCode();
        onChanged();
        return this;
      }
      /**
       * <code>required string respCode = 2;</code>
       */
      public Builder setRespCodeBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        respCode_ = value;
        onChanged();
        return this;
      }

      private com.google.protobuf.LazyStringList desc_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      private void ensureDescIsMutable() {
        if (!((bitField0_ & 0x00000004) == 0x00000004)) {
          desc_ = new com.google.protobuf.LazyStringArrayList(desc_);
          bitField0_ |= 0x00000004;
         }
      }
      /**
       * <code>repeated string desc = 3;</code>
       */
      public com.google.protobuf.ProtocolStringList
          getDescList() {
        return desc_.getUnmodifiableView();
      }
      /**
       * <code>repeated string desc = 3;</code>
       */
      public int getDescCount() {
        return desc_.size();
      }
      /**
       * <code>repeated string desc = 3;</code>
       */
      public String getDesc(int index) {
        return desc_.get(index);
      }
      /**
       * <code>repeated string desc = 3;</code>
       */
      public com.google.protobuf.ByteString
          getDescBytes(int index) {
        return desc_.getByteString(index);
      }
      /**
       * <code>repeated string desc = 3;</code>
       */
      public Builder setDesc(
          int index, String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  ensureDescIsMutable();
        desc_.set(index, value);
        onChanged();
        return this;
      }
      /**
       * <code>repeated string desc = 3;</code>
       */
      public Builder addDesc(
          String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  ensureDescIsMutable();
        desc_.add(value);
        onChanged();
        return this;
      }
      /**
       * <code>repeated string desc = 3;</code>
       */
      public Builder addAllDesc(
          Iterable<String> values) {
        ensureDescIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, desc_);
        onChanged();
        return this;
      }
      /**
       * <code>repeated string desc = 3;</code>
       */
      public Builder clearDesc() {
        desc_ = com.google.protobuf.LazyStringArrayList.EMPTY;
        bitField0_ = (bitField0_ & ~0x00000004);
        onChanged();
        return this;
      }
      /**
       * <code>repeated string desc = 3;</code>
       */
      public Builder addDescBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  ensureDescIsMutable();
        desc_.add(value);
        onChanged();
        return this;
      }
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:SubscribeResp)
    }

    // @@protoc_insertion_point(class_scope:SubscribeResp)
    private static final SubscribeResp DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new SubscribeResp();
    }

    public static SubscribeResp getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    @Deprecated public static final com.google.protobuf.Parser<SubscribeResp>
        PARSER = new com.google.protobuf.AbstractParser<SubscribeResp>() {
      public SubscribeResp parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
          return new SubscribeResp(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<SubscribeResp> parser() {
      return PARSER;
    }

    @Override
    public com.google.protobuf.Parser<SubscribeResp> getParserForType() {
      return PARSER;
    }

    public SubscribeResp getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_SubscribeResp_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_SubscribeResp_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\023SubscribeResp.proto\"A\n\rSubscribeResp\022\020" +
      "\n\010subReqId\030\001 \002(\005\022\020\n\010respCode\030\002 \002(\t\022\014\n\004de" +
      "sc\030\003 \003(\tB2\n\034com.wcs.learn.netty.protobuf" +
      "B\022SubscribeRespProto"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_SubscribeResp_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_SubscribeResp_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_SubscribeResp_descriptor,
        new String[] { "SubReqId", "RespCode", "Desc", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
