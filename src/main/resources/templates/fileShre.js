//const CHUNK_SIZE = 5 * 1024 * 1024;
//
//const fileInput =
//    document.getElementById("fileInput");
//
//const uploadBtn =
//    document.getElementById("uploadBtn");
//
//const progressBar =
//    document.getElementById("progressBar");
//
//const progressText =
//    document.getElementById("progressText");
//
//const message =
//    document.getElementById("message");
//
//uploadBtn.addEventListener(
//    "click",
//    uploadFile
//);
//
//async function uploadFile() {
//
//    try {
//
//        const file = fileInput.files[0];
//
//        if (!file) {
//
//            alert("Select a file");
//
//            return;
//        }
//
//        message.innerText = "";
//
//        progressBar.value = 0;
//
//        progressText.innerText = "0%";
//
//        const totalChunks = Math.ceil(
//            file.size / CHUNK_SIZE
//        );
//
//        // =========================
//        // STEP 1 INIT UPLOAD
//        // =========================
//
//        const initResponse = await fetch(
//            "http://localhost:8080/api/files/upload/init",
//            {
//                method: "POST",
//
//                headers: {
//                    "Content-Type":
//                        "application/json",
//
//                    Authorization:
//                        "Bearer " +
//                        localStorage.getItem(
//                            "token"
//                        )
//                },
//
//                body: JSON.stringify({
//
//                    filename: file.name,
//
//                    fileSize: file.size,
//
//                    totalChunks: totalChunks
//                })
//            }
//        );
//
//        const initData =
//            await initResponse.json();
//
//        const fileId = initData.fileId;
//
//        console.log("FILE ID:", fileId);
//
//        // =========================
//        // STEP 2 CHUNK UPLOAD
//        // =========================
//
//        for (
//            let chunkIndex = 0;
//            chunkIndex < totalChunks;
//            chunkIndex++
//        ) {
//
//            const start =
//                chunkIndex * CHUNK_SIZE;
//
//            const end = Math.min(
//                start + CHUNK_SIZE,
//                file.size
//            );
//
//            const chunk =
//                file.slice(start, end);
//
//            const formData = new FormData();
//
//            formData.append(
//                "fileId",
//                fileId
//            );
//
//            formData.append(
//                "chunkIndex",
//                chunkIndex
//            );
//
//            formData.append(
//                "file",
//                chunk
//            );
//
//            await fetch(
//                "http://localhost:8080/api/files/upload/chunk",
//                {
//                    method: "POST",
//
//                    headers: {
//                        Authorization:
//                            "Bearer " +
//                            localStorage.getItem(
//                                "token"
//                            )
//                    },
//
//                    body: formData
//                }
//            );
//
//            // =========================
//            // UPDATE PROGRESS
//            // =========================
//
//            const progress = Math.floor(
//                (
//                    (chunkIndex + 1)
//                    / totalChunks
//                ) * 100
//            );
//
//            progressBar.value = progress;
//
//            progressText.innerText =
//                progress + "%";
//
//            console.log(
//                `Chunk ${chunkIndex} uploaded`
//            );
//        }
//
//        // =========================
//        // STEP 3 COMPLETE UPLOAD
//        // =========================
//
//        await fetch(
//            `http://localhost:8080/api/files/upload/complete/${fileId}`,
//            {
//                method: "POST",
//
//                headers: {
//                    Authorization:
//                        "Bearer " +
//                        localStorage.getItem(
//                            "token"
//                        )
//                }
//            }
//        );
//
//        message.innerText =
//            "Upload Complete";
//
//    } catch (error) {
//
//        console.error(error);
//
//        message.innerText =
//            "Upload Failed";
//    }
//}