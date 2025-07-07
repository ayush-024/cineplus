import { EventData, Page } from '@nativescript/core';
import { PlayerPageModel } from './player-page-model';

export function navigatingTo(args: EventData) {
  const page = <Page>args.object;
  const context = page.navigationContext;
  page.bindingContext = new PlayerPageModel(context.content, context.type);
}

export function onVideoFinished(args: EventData) {
  const page = <Page>args.object.page;
  const model = page.bindingContext as PlayerPageModel;
  model.onVideoFinished();
}

export function onVideoLoaded(args: EventData) {
  const page = <Page>args.object.page;
  const model = page.bindingContext as PlayerPageModel;
  model.onVideoLoaded();
}

export function onPlayPauseTap(args: EventData) {
  const page = <Page>args.object.page;
  const model = page.bindingContext as PlayerPageModel;
  model.togglePlayPause();
}

export function onPreviousTap(args: EventData) {
  const page = <Page>args.object.page;
  const model = page.bindingContext as PlayerPageModel;
  model.previousContent();
}

export function onNextTap(args: EventData) {
  const page = <Page>args.object.page;
  const model = page.bindingContext as PlayerPageModel;
  model.nextContent();
}

export function onSettingsTap(args: EventData) {
  const page = <Page>args.object.page;
  const model = page.bindingContext as PlayerPageModel;
  model.toggleSettings();
}

export function onCloseTap(args: EventData) {
  const page = <Page>args.object.page;
  const model = page.bindingContext as PlayerPageModel;
  model.closePlayer();
}

export function onSeekChange(args: any) {
  const page = args.object.page;
  const model = page.bindingContext as PlayerPageModel;
  model.seekTo(args.value);
}

export function onQualityTap(args: EventData) {
  const page = <Page>args.object.page;
  const model = page.bindingContext as PlayerPageModel;
  const button = args.object as any;
  model.changeQuality(button.text);
}

export function onAudioTap(args: EventData) {
  const page = <Page>args.object.page;
  const model = page.bindingContext as PlayerPageModel;
  const button = args.object as any;
  model.changeAudio(button.text);
}

export function onSubtitleTap(args: EventData) {
  const page = <Page>args.object.page;
  const model = page.bindingContext as PlayerPageModel;
  const button = args.object as any;
  model.changeSubtitle(button.text);
}

export function onCloseSettingsTap(args: EventData) {
  const page = <Page>args.object.page;
  const model = page.bindingContext as PlayerPageModel;
  model.toggleSettings();
}