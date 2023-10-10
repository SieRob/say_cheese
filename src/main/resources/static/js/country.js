"use strict"
import {byId, toon} from "./util.js";
const countryId = sessionStorage.getItem("countryId");



const resp = await fetch(`country/${countryId}`);
if (resp.ok) {
    const country = await resp.json();
    console.log(country);

    byId("countryNaam").innerText = country.name;
    const countryUL = byId("cheeses")

    for (const cheese of country.cheese) {

        const li = document.createElement("li")
        const a = document.createElement("a");
        a.innerText = cheese.name;
        a.href = "details.html";
        a.onclick = function () {
            sessionStorage.setItem("cheeseId", JSON.stringify(cheese.id));
            toon("select")
        };

        li.appendChild(a)
        countryUL.appendChild(li);
    }
}