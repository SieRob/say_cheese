"use strict"
import {byId, toon} from "./util.js";
const cheeseId = sessionStorage.getItem("cheeseId");


const resp = await fetch(`cheese/${cheeseId}`);
if (resp.ok) {
    const cheeseDetails = await resp.json();
    console.log(cheeseDetails);
    const countryUL = byId("cheeseName")

    for (const detail of cheeseDetails) {
        byId("cheeseName").innerText = detail.cheeseName
        byId("country").innerText = detail.country
        byId("colour").innerText = detail.colour
        byId("vegetarian").innerText = (detail.vegetarian) ? "Yes" : "No";

        for(const flavour of detail.flavours){
            const brPoint = document.createElement("br")
            byId("flavours").innerText += flavour
            byId("flavours").appendChild(brPoint)
        }

        for(const animals of detail.animals){
            const brPoint = document.createElement("br")
            byId("animals").innerText += animals
            byId("animals").appendChild(brPoint)
        }

        const likes = detail.likes;
        const dislikes = detail.dislikes;
        const total = likes + dislikes;
        let resultLikes = (likes / total) * 100
        let resultDislikes= (dislikes / total) * 100

        byId("likes").innerText = likes + " = " + ((isNaN(resultLikes)) ? 0 : Math.round(resultLikes * 100) / 100) + "%"
        byId("dislikes").innerText = dislikes + " = " + ((isNaN(resultDislikes)) ? 0 : Math.round(resultDislikes * 100) / 100) + "%"

        for(const webpages of detail.webpages){
            const brPoint = document.createElement("br")
            const a = document.createElement("a");
            a.href = webpages
            a.innerText = webpages
            byId("webpages").appendChild(a)
            byId("webpages").appendChild(brPoint)
        }

        byId("addLike").onclick = async function () {
            const resp = await fetch(`cheese/${detail.id}/like`,
                {
                    method: "POST",
                    headers: {'Content-Type': "application/json"},
                    body: {}
                });
            if (resp.ok) {
                location.reload()
            }
        }

        byId("addDislike").onclick = async function () {
            const resp = await fetch(`cheese/${detail.id}/dislike`,
                {
                    method: "POST",
                    headers: {'Content-Type': "application/json"},
                    body: {}
                });
            if (resp.ok) {
                location.reload()
            }
        }
    }
}