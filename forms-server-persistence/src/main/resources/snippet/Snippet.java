package snippet;

public class Snippet {
	<?xml version="1.0" encoding="UTF-8"?>
	<!DOCTYPE ejb-jar PUBLIC '-//Sun Microsystems, Inc.//DTD Enterprise JavaBeans 
	2.0//EN' 'http://java.sun.com/dtd/ejb-jar_2_0.dtd'>
	<ejb-jar>
	<enterprise-beans>
		<message-driven>
			<ejb-name>MessageBean</ejb-name>
			<ejb-class>samples.mdb.ejb.MessageBean</ejb-class>
			<transaction-type>Container</transaction-type>
			<message-driven-destination>
				<destination-type>javax.jms.Queue</destination-type>
			</message-driven-destination>
			<resource-ref>
				<res-ref-name>jms/QueueConnectionFactory</res-ref-name>
				<res-type>javax.jms.QueueConnectionFactory</res-type>
				<res-auth>Container</res-auth>
			</resource-ref>
		</message-driven>
	</enterprise-beans>
		<assembly-descriptor>
			<container-transaction>
				<method>
					<ejb-name>MessageBean</ejb-name>
					<method-intf>Bean</method-intf>
					<method-name>onMessage</method-name>
					<method-params>
						<method-param>javax.jms.Message</method-param>
					</method-params>
				</method>
			<trans-attribute>NotSupported</trans-attribute>
		</container-transaction>
	</assembly-descriptor
	</ejb-jar>
}

