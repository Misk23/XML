package com.xml.publications.DTO;

import com.xml.publications.model.ScientificPublication.ScientificPublication;

public class MessageEditPublicationDTO {
    private MessageDTO messageDTO;
    private ScientificPublication scientificPublication;

    public MessageEditPublicationDTO(){
        super();
    }

    public MessageEditPublicationDTO(MessageDTO messageDTO, ScientificPublication scientificPublication) {
        this.messageDTO = messageDTO;
        this.scientificPublication = scientificPublication;
    }

    public ScientificPublication getScientificPublication() {
        return scientificPublication;
    }

    public void setScientificPublication(ScientificPublication scientificPublication) {
        this.scientificPublication = scientificPublication;
    }

    public MessageDTO getMessageDTO() {
        return messageDTO;
    }

    public void setMessageDTO(MessageDTO messageDTO) {
        this.messageDTO = messageDTO;
    }
}
