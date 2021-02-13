package Comandos.JWT;

import Comandos.ComandoBase;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import ucab.empresae.dtos.DtoResponse;
import ucab.empresae.entidades.UsuarioEntity;

import java.util.Date;

public class ComandoJWT extends ComandoBase<String> {

    private final DtoResponse dtoResponse;
    private String jwt;

    public ComandoJWT(DtoResponse dtoResponse) {

        this.dtoResponse = dtoResponse;

    }

    @Override
    public void execute() throws Exception {

        String key = "equipoE";
        long tiempo = System.currentTimeMillis();

        this.jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, key)
                .setIssuedAt(new Date(tiempo))
                .setExpiration(new Date(tiempo+900000))
                .claim("objeto", this.dtoResponse.getObjeto())
                .compact();

    }

    @Override
    public String getResult() throws Exception {
        execute();
        return this.jwt;
    }
}
