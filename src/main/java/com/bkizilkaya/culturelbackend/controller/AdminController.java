package com.bkizilkaya.culturelbackend.controller;

import com.bkizilkaya.culturelbackend.dto.artwork.response.ArtworkResponseDTO;
import com.bkizilkaya.culturelbackend.service.abstraction.ArtworkService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {
    private final ArtworkService artworkService;


    public AdminController(ArtworkService artworkService) {
        this.artworkService = artworkService;
    }

    @GetMapping("/admin")
    public String adminPanel(Model model) {
        return "index";
    }

    @GetMapping("/artworks-list")
    public String artworkPanel(Model model) {
        List<ArtworkResponseDTO> allArtworks = artworkService.getAllArtworks();
        model.addAttribute("artwork", allArtworks);
        return "artworks";
    }
}
