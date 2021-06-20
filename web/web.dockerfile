FROM node:16.3.0-alpine

WORKDIR /src

COPY package*.json /src/


RUN npm install -g @vue/cli && npm install -g @vue/cli-init && npm install -g @quasar/cli && npm install

# RUN npm install
# RUN yarn global add @quasar/cli
# RUN yarn

COPY ./ /src
