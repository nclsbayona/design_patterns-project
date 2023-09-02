FROM docker.io/node:lts-alpine3.17
WORKDIR /app
COPY . .
RUN npm install -g @angular/cli
RUN npm install
ENTRYPOINT ["npm", "run", "start"]