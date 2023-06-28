package com.ipiecoles.java.eval.th330.Controller;

import com.ipiecoles.java.eval.th330.model.Artist;
import com.ipiecoles.java.eval.th330.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InfoController {

    @Autowired
    private ArtistService artistService;

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/artists/{id}"
    )
    public String infoArtist(final ModelMap model, @PathVariable(value = "id") Long id) {
        Artist artist = artistService.findById(id);
        model.put("artist", artist);
        return "detailArtist";
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/artists"
    )
    public String listArtists(@RequestParam (value = "page", defaultValue = "0")Integer page,
                              @RequestParam (value = "size", defaultValue = "10") Integer size,
                              @RequestParam (value = "sortProperty", defaultValue = "name") String sortProperty,
                              @RequestParam (value = "sortDirection", defaultValue = "ASC" )String sortDirection,
                              final  ModelMap model ) {
        Page<Artist> artists = artistService.findAllArtists(page, size, sortProperty, sortDirection);
        model.put("artists", artists);
        model.put("start", page * size + 1);
        model.put("end", page * size + artists.getNumberOfElements());

        return "listeArtists";
    }




//    @RequestMapping(
//            method = RequestMethod.GET,
//            value = "/artists", params = "name"
//    )
//    public String searchByName(@RequestParam String name, final ModelMap model) {
//        model.put("artist", artistService.findByNameLikeIgnoreCase());
//        return "detailArtist"; //le recherche se font de maniere paginé (en utilisant liste artist), comme pour la liste des employés
//    }


}
