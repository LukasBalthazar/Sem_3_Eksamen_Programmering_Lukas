/*import { fetchAnyUrl, restDelete, postObjectAsJson, putObjectAsJson } from "./modulejson.js";

// Use ONE of these depending on A/B:
const urlAlbums = "/albums/";                         // Option A (static under Spring)

const tableBody = document.getElementById("tableBody");

function rowHtml(a) {
    return `
    <tr>
      <td>${a.id}</td>
      <td>${a.title ?? ""}</td>
      <td>${a.artist ?? ""}</td>
      <td>${a.available ? "Yes" : "No"}</td>
      <td>
        <button data-id="${a.id}" class="del">Delete</button>
      </td>
    </tr>`;
}

async function loadAlbums() {
    const data = await fetchAnyUrl(urlAlbums);
    tableBody.innerHTML = data.map(rowHtml).join("");
    document.querySelectorAll(".del").forEach(btn => {
        btn.onclick = async () => {
            await restDelete(urlAlbums + btn.dataset.id);
            await loadAlbums();
        };
    });
}

document.addEventListener("DOMContentLoaded", loadAlbums);

// Optional: support a super-simple create form if you add it to index.html
const form = document.getElementById("createForm");
if (form) {
    form.onsubmit = async (e) => {
        e.preventDefault();
        const fd = new FormData(form);
        const body = {
            title: fd.get("title"),
            artist: fd.get("artist"),
            available: fd.get("available") === "on"
        };
        await postObjectAsJson(urlAlbums, body);
        form.reset();
        await loadAlbums();
    };
}*/



