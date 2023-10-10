"use strict"
import {byId, toon, verberg} from "./util.js";

byId("zoek").onclick = async function () {
    verbergKazenEnFouten();
    const woordInput = byId("woord");
    if (woordInput.checkValidity()) {
        findKaasByWoord(woordInput.value);
    } else {
        toon("naamFout");
        woordInput.focus();
    }
};
function verbergKazenEnFouten() {
    verberg("naamTable");
    verberg("naamFout");
    verberg("storing");
}

async function findKaasByWoord(woord) {
    const resp = await fetch(`cheese?naamBevat=${woord}`);
    if (resp.ok) {
        const kazen = await resp.json();
        const tableBody = byId("tableBody");

        while (tableBody.firstElementChild){
            tableBody.removeChild(tableBody.firstElementChild)
        }

        toon("naamTable");
        for (const kaas of kazen){
            console.log(kaas)

            const tr = tableBody.insertRow();

            const a = document.createElement("a")
            a.href="details.html";
            a.innerText="detail";
            a.onclick = function (){
                sessionStorage.setItem("cheeseId", kaas.id)
            }

            tr.insertCell().innerText = kaas.name;
            tr.insertCell().innerText = kaas.country;
            tr.insertCell().innerText = kaas.colour;
            tr.insertCell().appendChild(a)
        }
    }
}