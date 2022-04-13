import { setupWorker, rest } from "msw";

// test
const worker = setupWorker(
  rest.get("/card", (req, res, ctx) => {
    return res(
      ctx.json({
        message: "Mocked response JSON body",
      })
    );
  })
);

// 3. Start the Service Worker.
worker.start();
