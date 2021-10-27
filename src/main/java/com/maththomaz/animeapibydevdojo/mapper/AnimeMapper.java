package com.maththomaz.animeapibydevdojo.mapper;

import com.maththomaz.animeapibydevdojo.domain.Anime;
import com.maththomaz.animeapibydevdojo.dto.request.AnimePostRequestBody;
import com.maththomaz.animeapibydevdojo.dto.request.AnimePutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class AnimeMapper {
    public static final AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);

    public abstract Anime toAnime(AnimePostRequestBody animePostRequestBody);

    public abstract Anime toAnime(AnimePutRequestBody animePutRequestBody);
}
