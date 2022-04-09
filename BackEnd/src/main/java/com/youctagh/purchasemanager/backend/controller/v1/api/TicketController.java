package com.youctagh.purchasemanager.backend.controller.v1.api;


import com.youctagh.purchasemanager.backend.controller.v1.dto.mapper.TicketMapper;
import com.youctagh.purchasemanager.backend.controller.v1.dto.model.TicketDTO;
import com.youctagh.purchasemanager.backend.controller.v1.dto.response.Response;
import com.youctagh.purchasemanager.backend.controller.v1.request.TicketRequest;
import com.youctagh.purchasemanager.backend.domain.Ticket;
import com.youctagh.purchasemanager.backend.service.TicketServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @Author YoucTagh
 */
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(maxAge = 3600)
public class TicketController {


    private final TicketServiceImpl ticketService;

    public TicketController(TicketServiceImpl ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/ticket")
    public Response getAllTickets() {
        return Response
                .ok()
                .setPayload(ticketService.findAll());
    }

    @GetMapping("/ticket/{id}")
    public Response getOneTicket(@PathVariable Long id) {
        return Response
                .ok()
                .setPayload(ticketService.findById(id));
    }

    @PostMapping("/ticket")
    public Response addNewTicket(@RequestBody TicketRequest ticketRequest) {
        return Response
                .ok()
                .setPayload(addTicket(ticketRequest));
    }

    private Response addTicket(TicketRequest ticketRequest) {

        return Response.ok().setPayload(
                ticketService
                        .addTicket(TicketMapper.requestToTicket(ticketRequest)));
    }

    @DeleteMapping("/ticket")
    public Response deleteATicket(@RequestBody TicketRequest ticketRequest) {
        return Response
                .ok()
                .setPayload(deleteTicket(ticketRequest));
    }

    private Optional<TicketDTO> deleteTicket(TicketRequest ticketRequest) {
        return ticketService.deleteTicket(
                (Ticket) new Ticket()
                        .setId(ticketRequest.getId()));
    }

    @PutMapping("/ticket")
    public Response updateATicket(@RequestBody TicketRequest ticketRequest) {
        return Response
                .ok()
                .setPayload(updateTicket(ticketRequest));
    }

    private TicketDTO updateTicket(TicketRequest ticketRequest) {

        return ticketService
                .updateTicket(TicketMapper.requestToTicket(ticketRequest))
                .get();
    }

}
