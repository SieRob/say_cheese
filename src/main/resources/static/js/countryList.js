"use strict"
import {byId, toon} from "./util.js";


const resp = await fetch("country");
if (resp.ok) {
    const countries = await resp.json();
    const countryUL = byId("countries")

    for (const country of countries) {
        const li = document.createElement("li")
        const a = document.createElement("a");
        a.innerText = country.naam;
        a.href = "countries.html";
        //a.href = "country/"+country.id;
        a.onclick = function () {
            sessionStorage.setItem("countryId", JSON.stringify(country.id));

            const SelectedCountry = byId("SelectedCountry");
            SelectedCountry.innerText = country.naam

            toon("select")
        };

        li.appendChild(a)
        countryUL.appendChild(li);
    }
}