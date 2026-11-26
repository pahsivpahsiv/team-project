package network.client.messages;

/**
 * This program's version of InputData (and OutputData).
 * Due for a rename.
 * Either rename, and use for both.
 * Or have ClientMessage and ServerMessage both extend a single interface.
 * Or keep those separate, and make messaging classes use a generic.
 * (i.e. NetworkRequestDecoder<ClientMessage>)
 */
public interface ClientMessage {}
