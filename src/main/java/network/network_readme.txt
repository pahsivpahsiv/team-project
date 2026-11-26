
11/25/2026
I figured I'd add some info in the project itself as well, for quick reference.
A more in-depth explanation will be in the server.
I've mainly been focused on the server-side message-receiving leg of the code so far,
but I've created more-or-less all the classes we need I think. (Naming still needs work).

I tried to make integration as seamless as possible.
The idea is for the client-side controller and server-side presenter to use MessageBuilder to create a
NetworkRequest object, and pass it to the relevant entry-point (NetworkInputBoundary and ServerOutputBoundary).
The NetworkRequest will then be sent to the relevant receivers, sent to and decoded by MessageDestination,
which will then make the necessary calls to server-side use cases and client-side presenters.

A lot of work left to do though. We're going to have to write a lot of tests too, and figure out how to
actually find connections over the internet.

I'm also going to look into where in the architecture we can slot in the bypass for the single-player mode.
I think we can likely just make single-player classes that implement the Network boundaries, and pass their
NetworkRequests straight to MessageDestination. I can't think of any reason why that wouldn't work (though
we may need to move where we do the decoding, which shouldn't affect anything).
  - Zhanshuo