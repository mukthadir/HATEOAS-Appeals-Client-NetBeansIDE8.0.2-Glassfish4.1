package com.mhchoudh.appeal.client.network;

import java.net.URI;

import com.mhchoudh.appeal.client.activities.CannotCancelException;
import com.mhchoudh.appeal.client.activities.CannotUpdateAppealException;
import com.mhchoudh.appeal.client.activities.DuplicateFormatException;
import com.mhchoudh.appeal.client.activities.InvalidFormatException;
import com.mhchoudh.appeal.client.activities.MalformedAppealException;
import com.mhchoudh.appeal.client.activities.NotFoundException;
import com.mhchoudh.appeal.client.activities.ServiceFailureException;
import com.mhchoudh.appeal.model.Appeal;
import com.mhchoudh.appeal.model.AppealFormat;
import com.mhchoudh.appeal.representations.AppealRepresentation;
import com.mhchoudh.appeal.representations.FormatAppealRepresentation;
import com.mhchoudh.appeal.representations.ReplyRepresentation;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;

public class HttpBinding {

    private static final String APPEALS_MEDIA_TYPE = "application/vnd.cse564-appeals+xml";

    public AppealRepresentation createAppeal(Appeal appeal, URI appealUri) throws MalformedAppealException, ServiceFailureException {
        Client client = Client.create();
        ClientResponse response = client.resource(appealUri).accept(APPEALS_MEDIA_TYPE).type(APPEALS_MEDIA_TYPE).post(ClientResponse.class, new AppealRepresentation(appeal));

        int status = response.getStatus();

        if (status == 400) {
            throw new MalformedAppealException();
        } else if (status == 500) {
            throw new ServiceFailureException();
        } else if (status == 201) {
            return response.getEntity(AppealRepresentation.class);
        }

        throw new RuntimeException(String.format("Unexpected response [%d] while creating appeal resource [%s]", status, appealUri.toString()));
    }
    
    public AppealRepresentation retrieveAppeal(URI appealUri) throws NotFoundException, ServiceFailureException {
        Client client = Client.create();
        ClientResponse response = client.resource(appealUri).accept(APPEALS_MEDIA_TYPE).get(ClientResponse.class);

        int status = response.getStatus();

        if (status == 404) {
            throw new NotFoundException ();
        } else if (status == 500) {
            throw new ServiceFailureException();
        } else if (status == 200) {
            return response.getEntity(AppealRepresentation.class);
        }

        throw new RuntimeException(String.format("Unexpected response while retrieving appeal resource [%s]", appealUri.toString()));
    }

    public AppealRepresentation updateAppeal(Appeal appeal, URI appealUri) throws MalformedAppealException, ServiceFailureException, NotFoundException,
            CannotUpdateAppealException {
        Client client = Client.create();
        ClientResponse response = client.resource(appealUri).accept(APPEALS_MEDIA_TYPE).type(APPEALS_MEDIA_TYPE).post(ClientResponse.class, new AppealRepresentation(appeal));

        int status = response.getStatus();

        if (status == 400) {
            throw new MalformedAppealException();
        } else if (status == 404) {
            throw new NotFoundException();
        } else if (status == 409) {
            throw new CannotUpdateAppealException();
        } else if (status == 500) {
            throw new ServiceFailureException();
        } else if (status == 200) {
            return response.getEntity(AppealRepresentation.class);
        }

        throw new RuntimeException(String.format("Unexpected response [%d] while udpating appeal resource [%s]", status, appealUri.toString()));
    }

    public AppealRepresentation deleteAppeal(URI appealUri) throws ServiceFailureException, CannotCancelException, NotFoundException {
        Client client = Client.create();
        ClientResponse response = client.resource(appealUri).accept(APPEALS_MEDIA_TYPE).delete(ClientResponse.class);

        int status = response.getStatus();
        if (status == 404) {
            throw new NotFoundException();
        } else if (status == 405) {
            throw new CannotCancelException();
        } else if (status == 500) {
            throw new ServiceFailureException();
        } else if (status == 200) {
            return response.getEntity(AppealRepresentation.class);
        }

        throw new RuntimeException(String.format("Unexpected response [%d] while deleting appeal resource [%s]", status, appealUri.toString()));
    }

    public FormatAppealRepresentation formatAp(AppealFormat format, URI formatUri) throws InvalidFormatException, NotFoundException, DuplicateFormatException,
            ServiceFailureException, InterruptedException {
        Client client = Client.create();
        ClientResponse response = client.resource(formatUri).accept(APPEALS_MEDIA_TYPE).type(APPEALS_MEDIA_TYPE).put(ClientResponse.class, new FormatAppealRepresentation(format));

        int status = response.getStatus();
        if (status == 400) {
            throw new InvalidFormatException();
        } else if (status == 404) {
            throw new NotFoundException();
        } else if (status == 405) {
            throw new DuplicateFormatException();
        } else if (status == 500) {
            throw new ServiceFailureException();
        } else if (status == 201) {
            return response.getEntity(FormatAppealRepresentation.class);
        }

        throw new RuntimeException(String.format("Unexpected response [%d] while creating format resource [%s]", status, formatUri.toString()));
    }

    public ReplyRepresentation retrieveReply(URI replyUri) throws NotFoundException, ServiceFailureException {
        Client client = Client.create();
        ClientResponse response = client.resource(replyUri).accept(APPEALS_MEDIA_TYPE).get(ClientResponse.class);

        int status = response.getStatus();
        if (status == 404) {
            throw new NotFoundException();
        } else if (status == 500) {
            throw new ServiceFailureException();
        } else if (status == 200) {
            return response.getEntity(ReplyRepresentation.class);
        }
        
        throw new RuntimeException(String.format("Unexpected response [%d] while retrieving reply resource [%s]", status, replyUri.toString()));
    }
}
