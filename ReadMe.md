# Default exchange

The default exchange is a direct exchange with no name (empty string) pre-declared by the broker. Every queue that is created is automatically bound to it with
a routing key which is the same as the queue name.

# Direct exchange

Message is sent to the queue having the same routing key specified in the binding rule.

# Fanout exchange

The message is routed to all the available bounded queues. The routing key if provided is completely ignored.

# Topic exchange

Routing key is used again, but unlike in direct exchange here the routing key of the exchange and the bound queues should not necessarily be an exact match.
Using regular expressions like wildcard we can send the exchange to multiple bound queues.

# Headers exchange

Routing queue is selected based on message header. This is similar to topic exchange type, but here we can specify complex criteria for selecting routing
queues. 